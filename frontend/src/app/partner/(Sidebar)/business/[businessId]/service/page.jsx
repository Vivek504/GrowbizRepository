import { getServerSession } from "next-auth";
import { authOptions } from "@/app/api/auth/[...nextauth]/route";
import ViewServices from "./components/ViewServices";
import {
    getAllSubCategories,
    getAllSubCategoriesForCategory,
} from "@/services/subCategoriesServices";
import { getServicesByBusinessId } from "@/services/servicesService";
import { ScrollArea } from "@/components/ui/scroll-area";
import { getBusiness } from "@/services/businessService";

export default async function ServiceManagement(context) {
    const authSession = await getServerSession(authOptions);
    const businessId = Number(context.params.businessId);
    let business = await getBusiness(
        authSession.user.email,
        authSession.apiToken
    );
    business = business?.businesses[0];
    const subCategories = await getAllSubCategoriesForCategory(
        authSession.apiToken,
        business.category.categoryID
    );
    function convertHHMMToMinutes(formattedTime) {
        const [hours, minutes] = formattedTime;
        const totalMinutes = hours * 60 + minutes;
        return totalMinutes;
    }

    let fetchedServices = await getServicesByBusinessId(
        authSession.apiToken,
        businessId
    );
    fetchedServices = fetchedServices.map((service) => ({
        ...service,
        timeRequired: convertHHMMToMinutes(service.timeRequired),
    }));

    const predefinedServices = subCategories.map((subcategory) => {
        return {
            predefinedServiceId: subcategory.subCategoryID,
            predefinedServiceName: subcategory.name,
        };
    });

    return (
        <>
            <ScrollArea className="h-[100vh] w-[95vw] rounded-md p-4">
                <div>
                    <ViewServices
                        authSession={authSession}
                        predefinedServices={predefinedServices}
                        businessId={businessId}
                        fetchedServices={fetchedServices}
                        subCategories={subCategories}
                    />
                </div>
            </ScrollArea>
        </>
    );
}
